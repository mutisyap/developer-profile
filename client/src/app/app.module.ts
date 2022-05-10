import { NgModule, SecurityContext } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common'


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { HomeComponent } from './layouts/home/home.component';
import { BlogComponent } from './blog/blog.component';
import { CvComponent } from './cv/cv.component';
import { AdminComponent } from './admin/admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MarkdownModule, MarkedOptions } from 'ngx-markdown';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SanitizeHtmlPipe } from './sanitize-html-pipe';
import { AngularMarkdownEditorModule } from 'angular-markdown-editor';
import { LearnComponent } from './learn/learn.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    BlogComponent,
    CvComponent,
    AdminComponent,
    LearnComponent,
    SanitizeHtmlPipe
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    AngularMarkdownEditorModule,
    HttpClientModule,
    MarkdownModule.forRoot(
      {
        // loader: HttpClient,
        markedOptions: {
          provide: MarkedOptions,
          useValue: {
            gfm: true,
            breaks: true,
            pedantic: true,
            smartLists: true,
            smartypants: true,
          }
        },
        sanitize: SecurityContext.HTML,
      }
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
