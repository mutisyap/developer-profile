import { Component, OnInit } from '@angular/core';
import { ArticleService } from 'src/app/article.service';
import { JsonService } from 'src/app/json.service';
import { SeoService } from 'src/app/seo.service';

declare var require: any;


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private articleService: ArticleService, private seo: SeoService, private jsonService: JsonService) {
    this.seo.setSeoTags('Peter Mutisya - Software Engineer', 'What needs to be done must be done by we who are', 'assets/images/petro.jpg', true);
  }

  projectList: any = []; // require('../../../assets/json/projects.json');
  skillList: any = []; //require('../../../assets/json/skills.json');
  featuredItems: any = []; //  = require('../../../assets/json/featured.json');
  about: any = []; //  require('../../../assets/json/about.json');
  learn: any = []; // require('../../../assets/json/learn.json');
  blogs: any[] = []; // = require('../../../assets/json/blogs.json');

  ngOnInit(): void {
    this.jsonService.getJSONData('featured.json').subscribe(
      res => {
        this.featuredItems = res;
      },
      err => {
        this.featuredItems = require('../../../assets/json/featured.json');
      }
    )

    this.jsonService.getJSONData('about.json').subscribe(
      res => {
        this.about = res;
      },
      err => {
        this.about = require('../../../assets/json/about.json');
      }
    )

    this.jsonService.getJSONData('learn.json').subscribe(
      res => {
        this.learn = res;
      },
      err => {
        this.learn = require('../../../assets/json/learn.json');
      }
    )

    this.articleService.getArticles().subscribe(
      result => {
        this.blogs = result;

        this.blogs.forEach(blog => {
          if (blog.articleMd)
            blog.duration = Math.ceil(blog.articleMd.split(' ').length / 200) + ' min';
          if (blog.articleMd.split(' ').length / 200 > 1) {
            blog.duration += 's'
          }

          if (!blog.status) {
            blog.status = 'NEW';
          }
        });
      }
    )
  }

  dockerBlogs(): void {
    // window.location.href = "https://petermutisya.medium.com/building-docker-images-37405340eb6e";
  }

}
