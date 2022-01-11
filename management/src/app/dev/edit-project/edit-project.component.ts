import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter } from 'rxjs/operators';


@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.scss']
})
export class EditProjectComponent implements OnInit {

  projectId: number;

  constructor(private route: ActivatedRoute) {
    let projectIdStr = route.snapshot.queryParamMap.get('projectId');
    if (projectIdStr) {
      this.projectId = parseInt(projectIdStr)
    } else {
      this.projectId = -1;
    }
  }

  ngOnInit(): void {
  }

}
