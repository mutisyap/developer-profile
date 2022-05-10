import { Inject, Injectable } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser'
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class SeoService {

  constructor(private meta: Meta, private title: Title, @Inject(DOCUMENT) private doc: Document) { }

  setSeoTags(title: string, description: string, imageURL: string, isImageRelative?: boolean) {
    this.title.setTitle(title);

    this.meta.updateTag({
      property: 'description',
      content: description
    });

    if (isImageRelative) {
      imageURL = this.doc.location.origin + "/" + imageURL;
    }

    this.meta.updateTag({
      property: 'og:title',
      content: title
    });

    this.meta.updateTag({
      property: 'og:description',
      content: description
    });

    this.meta.updateTag({
      property: 'og:image',
      content: imageURL
    });

    this.meta.updateTag({
      property: 'twitter:title',
      content: title
    });

    this.meta.updateTag({
      property: 'twitter:description',
      content: description
    });

    this.meta.updateTag({
      property: 'twitter:image',
      content: imageURL
    });

  }
}
