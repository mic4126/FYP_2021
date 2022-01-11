import { Component, Input, OnInit } from '@angular/core';
import { LANG } from 'src/app/model/Lang';

@Component({
  selector: 'app-desc',
  templateUrl: './desc.component.html',
  styleUrls: ['./desc.component.scss']
})
export class DescComponent implements OnInit {

  @Input() projectId:number = -1;
  LANG = LANG;

  constructor() { }

  ngOnInit(): void {
  }

}
