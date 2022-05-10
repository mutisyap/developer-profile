import { Component, OnInit } from '@angular/core';
import { ArticleService } from 'src/app/article.service';
import { SeoService } from 'src/app/seo.service';

declare var require: any;


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private articleService: ArticleService, private seo: SeoService) {
    this.seo.setSeoTags('Peter Mutisya - Software Engineer', 'What needs to be done must be done by we who are', 'assets/images/petro.jpg', true);
  }

  projectList: any = require('../../../assets/json/projects.json');
  skillList: any = require('../../../assets/json/skills.json');
  featuredItems: any = require('../../../assets/json/featured.json');
  about: any = require('../../../assets/json/about.json');
  learn: any = require('../../../assets/json/learn.json');
  blogs: any[] = []; // = require('../../../assets/json/blogs.json');


  featuredItem = {
    title: "Natujenge",
    icon: "https://natujenge.ke/assets/images/logo-wordmark.svg",
    showText: false,
    description: 'A platform for people to share their knowledge and experience with each other.',
    link: 'https://natujenge.ke/',
    callToAction: 'Join us',
    imageSrc: ".../../../assets/love-the-bytes.png",
    callToActionLinks: [
      {
        'text': 'Follow',
        'icon': '../assets/youtube-brands.svg',
        'link': 'https://www.youtube.com/channel/UCJyqTEptQMHh5qo23GpKpQw'
      },
      {
        'text': 'Join Us',
        'icon': '../assets/whatsapp-brands.svg',
        'link': 'https://www.youtube.com/channel/UCJyqTEptQMHh5qo23GpKpQw'
      },
    ]
  };

  ngOnInit(): void {
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
