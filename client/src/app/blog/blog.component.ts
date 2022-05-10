import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MarkdownService } from 'ngx-markdown';
import { ArticleService } from '../article.service';
import { Meta, Title } from '@angular/platform-browser'
import { DOCUMENT } from '@angular/common';
import { SeoService } from '../seo.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  constructor(private articleService: ArticleService, private route: ActivatedRoute, private markdownService: MarkdownService, private seo: SeoService) { }

  article!: any;
  isLoading = false;

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        console.log('params : ', params);
        this.isLoading = true;
        this.articleService.getArticle(params['id']).subscribe(
          result => {
            this.article = result;

            let blogImage = 'assets/images/petro.jpg';
            let isImageRelative = true;

            if (this.article.metaImageURL) {
              blogImage = this.article.metaImageURL;
              isImageRelative= false;
            }

            this.seo.setSeoTags(this.article.title + " - by " + this.article.author, this.article.brief.substring(0, 140) + ' ... ', blogImage, isImageRelative);

            this.article.articleHTML = this.markdownService.compile(this.article.articleMd, true, false,
              { breaks: true, gfm: true, pedantic: false, smartLists: true, smartypants: true }
            )
          },
          err => {

          },
          () => {
            this.isLoading = false;
          }
        )
      }
    )
  }

}
