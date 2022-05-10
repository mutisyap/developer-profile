import { Component, OnInit } from '@angular/core';

declare var require: any;

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  learn: any = require('../../../assets/json/learn.json');

  constructor() { }

  showSidebar = false;

  ngOnInit(): void {
  }

}
