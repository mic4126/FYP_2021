import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Notice } from 'src/app/model/notice.model';
import { NoticeService } from 'src/app/services/notice.service';

const defaultClassList = ["alert"]
const defaultTimeout = 1000 * 10 //10 * 1000ms = 10s
// const defaultTimeout = -1 //10 * 1000ms = 10s
@Component({
  selector: 'app-notice',
  templateUrl: './notice.component.html',
  styleUrls: ['./notice.component.scss']
})
export class NoticeComponent implements OnInit, OnDestroy {

  @Input() additionalClasses: string = ""

  constructor(private noticeService: NoticeService) { }

  notices: Notice[] = [];

  noticeSubscript: Subscription = new Subscription();

  ngOnInit(): void {
    this.noticeSubscript = this.noticeService.onNotice().subscribe(
      (notice) => {

        if (notice?.timeout !== -1 && defaultTimeout !== -1) {
          setTimeout(() => {
            this.removeNotice(notice)
          }, notice.timeout || defaultTimeout);
        }
        this.notices.push(notice);

      }
    )
  }

  ngOnDestroy(): void {
    this.noticeSubscript.unsubscribe();
  }

  removeNotice(notice: Notice) {
    if (!this.notices.includes(notice)) {
      // user already remove, do nothing
      return;
    }
    this.notices = this.notices.filter(n => n !== notice)
  }

  getCSS(n: Notice): string {
    var classList = defaultClassList.concat(n.type, this.additionalClasses);
    var classes = classList.join(" ");
    return classes;
  }

}
