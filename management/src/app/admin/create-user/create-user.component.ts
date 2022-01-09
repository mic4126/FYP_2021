import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateUserModel } from 'src/app/model/CreateUserModel.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {

  createUserForm: FormGroup

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.createUserForm = fb.group(
      {
        "username": ['', Validators.required],
        "firstName": ['', Validators.required],
        "lastName": ['', Validators.required],
        "email": ['', [Validators.required, Validators.email]],
        "userType": ['', [Validators.required]]
      }
    )
  }

  ngOnInit(): void {
  }

  createUserFormSubmit(): void {
    if (!this.createUserForm.valid) {
      return;
    }
    const createUserInfo: CreateUserModel = <CreateUserModel>this.createUserForm.value
    console.log(createUserInfo);
    this.userService.createUser(createUserInfo).subscribe(() => {
      console.log("successful");
      this.createUserForm.reset()
    })

  }

}
