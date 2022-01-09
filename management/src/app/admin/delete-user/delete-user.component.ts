import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.scss']
})
export class DeleteUserComponent implements OnInit {

  users:Observable<UserInfo[]>;

  constructor(private userService:UserService) { 
    this.users = userService.getAllUsers();
  }

  ngOnInit(): void {
  }

  onDeleteClicked(username:string){
    console.log(username);
    this.userService.deleteUser(username).subscribe(() => {
      this.users = this.userService.getAllUsers();
    })
  }

}
