import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MarkdownService, MarkedOptions } from 'ngx-markdown';
import { DomSanitizer } from '@angular/platform-browser';
import { ArticleService } from '../article.service';
import { HttpEventType } from '@angular/common/http';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(protected formBuilder: FormBuilder, private markdownService: MarkdownService, private sanitizer: DomSanitizer, private articleService: ArticleService, @Inject(DOCUMENT) private doc: Document) { }

  previewMode = false;
  showModal = false;
  resourcesErrorMessage = {
    success: "",
    error: ""
  };

  article: any = {
    title: '',
    brief: '',
    article: '',
    author: 'Peter Mutisya',
    articleHTML: undefined,
    metaImageURL: undefined
  }

  articleForm = this.formBuilder.group({
    title: ['',],
    brief: [''],
    article: ['# Heading1\n ## Heading 2\n Image: ![Not Peter](https://eulu.co.ke/uploads/1d1dc999af144cc3bb5d6f2f5c363149.jpeg)'],
    author: ['Peter Mutisya'],
    metaImageURL: ['']
  })

  markedOptions = {
    gfm: true,
    breaks: true,
    pedantic: true,
    smartLists: true,
    smartypants: true
  }

  adminAction = 'manage';

  blogs: any = [];

  ngOnInit(): void {
    this.articleService.getArticles().subscribe(
      result => {
        this.blogs = result;

        this.blogs.forEach((blog: any) => {
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


  previewArticle() {
    this.previewMode = !this.previewMode;

    console.log('this.article : ', this.article)

    if (this.previewMode) {
      this.article = {
        title: this.articleForm.get(['title'])!.value,
        brief: this.articleForm.get(['brief'])!.value,
        article: this.articleForm.get(['article'])!.value,
        author: this.articleForm.get(['author'])!.value,
        articleHTML: this.markdownService.compile(this.articleForm.get(['article'])!.value, true, false,
          { breaks: true, gfm: true, pedantic: false, smartLists: true, smartypants: true }
        ),
      }
    }

    console.log(this.markdownService.compile('I am using __markdown__.'));
  }

  saveArticle(): void {

    const article = {
      title: this.articleForm.get(['title'])!.value,
      brief: this.articleForm.get(['brief'])!.value,
      articleMd: this.articleForm.get(['article'])!.value,
      author: this.articleForm.get(['author'])!.value,
      metaImageURL: this.articleForm.get(['metaImageURL'])!.value
    }
    this.articleService.createArticle(article).subscribe(
      result => {
        console.log('Successfully created blog: ', result)
      }
    )
  }

  toggleModal(): void {
    this.showModal = !this.showModal;

    this.resourcesErrorMessage.error = "";
    this.resourcesErrorMessage.success = "";
  }

  addResourceFile(event: any) {
    console.log('files : ', event.target.files);
    this.resourcesErrorMessage.error = "";
    this.resourcesErrorMessage.success = "";

    let resourceFile = event.target.files[0];

    if (resourceFile.size > 2100000) {
      this.resourcesErrorMessage.error = "Naah. Keep it under 2MB";
      this.clearFileInput(document.getElementById('uploadResources'));
      return;
    }

    // do uploading
    this.articleService.uploadFile(resourceFile).subscribe(
      result => {
        if (result.type == HttpEventType.UploadProgress) {
          const uploadProgress = Math.round(100 * (result.loaded / result.total));
          console.log('Upload progress : ', uploadProgress);
        } else if (result.type == HttpEventType.Response) {
          this.clearFileInput(document.getElementById('uploadResources'));

          let serverIp = this.doc.location.origin;

          if (serverIp.indexOf("localhost") > -1) {
            serverIp = "http://localhost:8080"
          }

          this.resourcesErrorMessage.success = "Successfully uploaded. File URL : " + serverIp + "/uploads/" + result.body.filename;
          console.log('result : ', result);
        }
      },
      error => {
        // this.resourcesErrorMessage.error = JSON.stringify(error);
        this.resourcesErrorMessage.error = error.message;
      }
    )



  }

  clearFileInput(ctrl: any) {
    try {
      ctrl.value = null;
    } catch (ex) { }
    if (ctrl.value) {
      ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
    }
  }
}
