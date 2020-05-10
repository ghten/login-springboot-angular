import { Routes, RouterModule, PreloadAllModules  } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { PagesComponent } from './pages/pages.component';
import { BlankComponent } from './pages/blank/blank.component';
import { NotFoundComponent } from './pages/errors/not-found/not-found.component';
import { ErrorComponent } from './pages/errors/error/error.component';

export const routes: Routes = [
    {
        path: '',
        component: PagesComponent, children: [
            { path: '', loadChildren: () => import('./pages/dashboard/dashboard.module').then(m => m.DashboardModule), data: { breadcrumb: 'Dashboard' } },
            { path: 'dashboard', loadChildren: () => import('./pages/dashboard/dashboard.module')
            .then(m => m.DashboardModule), data: { breadcrumb: 'Dashboard' } },
            { path: 'users', loadChildren: () => import('./pages/users/users.module').then(m => m.UsersModule),
            data: { breadcrumb: 'Users' } },
             { path: 'profile', loadChildren: () => import ('./pages/profile/profile.module').then(m => m.ProfileModule),
             data: { breadcrumb: 'Profile' } },
            { path: 'blank', component: BlankComponent, data: { breadcrumb: 'Blank page' } },
       ]
    },
    { path: 'login', loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule) },
    { path: 'forgot-password', loadChildren: () => import('./pages/forgot-password/forgot-password.module')
    .then(m => m.ForgotPasswordModule) },
    { path: 'restore-password', loadChildren: () => import('./pages/restore-password/restore-password.module')
    .then(m => m.RestorePasswordModule) },
    { path: 'register', loadChildren: () => import('./pages/register/register.module').then(m => m.RegisterModule) },
    { path: 'error', component: ErrorComponent, data: { breadcrumb: 'Error' } },
    { path: '**', component: NotFoundComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules,  // <- comment this line for enable lazy load
    // useHash: true
});
