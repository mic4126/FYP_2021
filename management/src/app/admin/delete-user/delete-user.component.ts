import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfo } from 'src/app/model/UserInfo.model';
import { NoticeService } from 'src/app/services/notice.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.scss']
})
export class DeleteUserComponent implements OnInit {

  users: Observable<UserInfo[]>;

  constructor(private userService: UserService,
    private ns: NoticeService) {
    this.users = userService.getAllUsers();
  }

  ngOnInit(): void {
  }

  onDeleteClicked(username: string) {
    console.log(username);
    this.userService.deleteUser(username).subscribe(
      {
        next: () => {
          this.users = this.userService.getAllUsers();
          this.ns.success(`User:${username} deleted`)
        },
        error: ()=>{
          this.ns.error("Failed to delete user.")
          this.users = this.userService.getAllUsers();
        }
      })
  }

}
