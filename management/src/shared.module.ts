import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { NgSelectModule } from "@ng-select/ng-select";
import { NoticeModule } from "./app/common/notice/notice.module";
import { HomeModule } from "./app/home/home.module";

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        HomeModule,
        NgSelectModule,
        FormsModule,
        NgbModule,
        NoticeModule,
    ],
    exports:[
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        HomeModule,
        NgSelectModule,
        FormsModule,
        NgbModule,
        NoticeModule,
    ]
})
export class SharedModule { }