import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CcwApplicationSharedModule } from 'app/shared/shared.module';
import { CcwApplicationCoreModule } from 'app/core/core.module';
import { CcwApplicationAppRoutingModule } from './app-routing.module';
import { CcwApplicationHomeModule } from './home/home.module';
import { CcwApplicationEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CcwApplicationSharedModule,
    CcwApplicationCoreModule,
    CcwApplicationHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CcwApplicationEntityModule,
    CcwApplicationAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class CcwApplicationAppModule {}
