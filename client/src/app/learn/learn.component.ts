import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SeoService } from '../seo.service';

declare var require: any;

@Component({
  selector: 'app-learn',
  templateUrl: './learn.component.html',
  styleUrls: ['./learn.component.css']
})
export class LearnComponent implements OnInit {

  constructor(private route: ActivatedRoute, private seo: SeoService) { }

  learn: any[] = require('../../assets/json/learn.json');

  currentLesson! : any;

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        console.log('params : ', params['topic'])

        this.learn.forEach(lesson => {
          if (lesson.title.toLowerCase() === params['topic']){
            this.currentLesson = lesson;

            this.seo.setSeoTags(this.currentLesson.title, this.currentLesson.description.substring(0, 140) + ' ... ', this.currentLesson.image, false);
          }
        });

        console.log('currentLesson : ', this.currentLesson);
      }
    )
    console.log('learn : ', this.learn)
  }

}
