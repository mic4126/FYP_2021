import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Notice, NOTICE_TYPE_ENUM } from '../model/notice.model';

@Injectable({
  providedIn: 'root'
})
export class NoticeService {

  private noticeSubject = new Subject<Notice>();

  onNotice(): Observable<Notice> {
    return this.noticeSubject.asObservable();
  }

  addNotice(n: Notice) {
    this.noticeSubject.next(n);
  }

  success(message: string) {
    this.addNotice({ type: NOTICE_TYPE_ENUM.SUCCESS, message: message });
  }


  error(message: string) {
    this.addNotice({
      type: NOTICE_TYPE_ENUM.ERROR, message: message
    });
  }

  constructor() { }
}
