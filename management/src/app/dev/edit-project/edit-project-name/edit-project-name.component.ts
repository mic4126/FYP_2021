import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LANG } from 'src/app/model/Lang';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-edit-project-name',
  templateUrl: './edit-project-name.component.html',
  styleUrls: ['./edit-project-name.component.scss']
})
export class EditProjectNameComponent implements OnInit {

  @Input() projectId:number = -1;  
  projectNameForm:FormGroup;

  constructor(private fb:FormBuilder,private projectService:ProjectService) { 
    this.projectNameForm = this.fb.group({
      "engName":['',[Validators.required]],
      "TCName":['',[]],
      "SCName":['',[]]
    })
  }

  ngOnInit(): void {
    this.setInitValue()
  }

  setInitValue(){    
     this.projectService.getProjectName(this.projectId,LANG.ENG).subscribe((resp) => {
       this.projectNameForm.controls['engName'].setValue(resp);
     })
     this.projectService.getProjectName(this.projectId,LANG.TC).subscribe((resp) => {
      this.projectNameForm.controls['TCName'].setValue(resp);
    })
    this.projectService.getProjectName(this.projectId,LANG.SC).subscribe((resp) => {
      this.projectNameForm.controls['SCName'].setValue(resp);
    })    
    console.log("Set Init Value Done");
    
  }

  onSubmit(){
    this.projectService.setProjectName(this.projectId,LANG.ENG, this.projectNameForm.controls['engName'].value).subscribe( () =>{
      console.log("Set Eng project Name done");
    })
    this.projectService.setProjectName(this.projectId,LANG.TC, this.projectNameForm.controls['TCName'].value).subscribe( () =>{
      console.log("Set TC project Name done");
    })
    this.projectService.setProjectName(this.projectId,LANG.SC, this.projectNameForm.controls['SCName'].value).subscribe( () =>{
      console.log("Set SC project Name done");
    })
  }



}
