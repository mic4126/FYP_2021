import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Contact } from 'src/app/model/Contact.model';
import { ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-edit-contact',
  templateUrl: './edit-contact.component.html',
  styleUrls: ['./edit-contact.component.scss']
})
export class EditContactComponent implements OnInit {

  @Input() projectId: number = -1;
  contactForm:FormGroup;

  constructor(private fb:FormBuilder,private projectService:ProjectService) {
    this.contactForm = fb.group({
      'email':['',[Validators.email]],
      'department':['',[]],
      'phoneNumber':['',[]],
      'projectId':['',[]]
    })
   }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(){
    this.projectService.getContact(this.projectId).subscribe( (contact) => {
      this.contactForm.patchValue(contact);
    })
  }

  onSubmit(){
    if (this.contactForm.invalid){
      return;
    }
    const contact:Contact = this.contactForm.value
    this.projectService.setContact(contact).subscribe(() => {
      console.log("Contact updated");    
    })

  }

}
