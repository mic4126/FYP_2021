import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { LANG } from 'src/app/model/Lang';
import { NoticeService } from 'src/app/services/notice.service';
import { ProjectService } from 'src/app/services/project.service';
import { LanguageServiceMode } from 'typescript';

@Component({
  selector: 'app-edit-desc',
  templateUrl: './edit-desc.component.html',
  styleUrls: ['./edit-desc.component.scss']
})
export class EditDescComponent implements OnInit {

  @Input() projectId: number = -1;
  @Input() lang: LANG = LANG.ENG;
  descFormGroup: FormGroup;
  descTextArea: FormControl;

  constructor(private fb: FormBuilder,
    private projectService: ProjectService,
    private ns: NoticeService) {
    this.descFormGroup = fb.group({
      "descTextArea": ['', []]
    })
    this.descTextArea = <FormControl>this.descFormGroup.get('descTextArea');
  }

  ngOnInit(): void {
    this.setDescFromDB();

  }

  private setDescFromDB() {
    this.projectService.getProjectDesc(this.projectId, this.lang).subscribe((resp) => {
      this.descTextArea.setValue(resp);
    });
  }

  onDescSubmit() {
    if (this.descFormGroup.dirty) {
      const desc = <string>this.descTextArea.value
      this.projectService.setProjectDesc(this.projectId, this.lang, desc).subscribe({
        next: () => {
          console.log("Success updated " + this.lang + " description");
          this.ns.success("Description updated.")
        },
        error: () => {
          this.ns.error("Description failed to update.");
          this.setDescFromDB();
        }
      })
    }
  }

}
