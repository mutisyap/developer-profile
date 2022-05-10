import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { BlogComponent } from './blog/blog.component';
import { CvComponent } from './cv/cv.component';
import { HomeComponent } from './layouts/home/home.component';
import { LearnComponent } from './learn/learn.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'blogs/:id',
    component: BlogComponent
  },
  {
    path: 'about',
    component: CvComponent
  },
  {
    path: 'admin-9d64',
    component: AdminComponent
  },
  {
    path: 'learn/:topic',
    component: LearnComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabledBlocking'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
