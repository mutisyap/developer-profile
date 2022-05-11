import { Component, OnInit } from '@angular/core';
import { JsonService } from 'src/app/json.service';

declare var require: any;

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  learn!: any[];

  constructor(private jsonService: JsonService) { }

  showSidebar = false;

  ngOnInit(): void {
    this.jsonService.getJSONData('learn.json').subscribe(
      res => {
        this.learn = res;
      },
      err => {
        this.learn = require('../../../assets/json/learn.json');
      }
    )
  }

}
