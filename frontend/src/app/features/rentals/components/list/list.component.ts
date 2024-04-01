import { Component } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { RentalsService } from '../../services/rentals.service';
import { Observable } from 'rxjs';
import { RentalsResponse } from '../../interfaces/api/rentalsResponse.interface';
import { Rental } from '../../interfaces/rental.interface';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
    styleUrls: ['./list.component.scss'],
})
export class ListComponent {
    private rentals$: Observable<RentalsResponse> = this.rentalsService.all();
    private rentals: any = [];
    private user = this.sessionService.user as User;

    constructor(
        private sessionService: SessionService,
        private matSnackBar: MatSnackBar,
        private rentalsService: RentalsService
    ) {}

    ngOnInit(): void {
        this.rentals$.subscribe((response) => {
            this.rentals = response;
        });
        this.user = this.sessionService.user as User;
    }

    public getRentals(): any {
        return this.rentals;
    }

    public isOwner(rental: Rental): boolean {
        return rental.owner.id === this.user.id;
    }

    public canCreate(): boolean {
        return this.user.roles.includes('ROLE_OWNER' || 'ROLE_ADMIN');
    }

    public deleteRental(rental: Rental): void {
        this.rentalsService.delete(rental.id).subscribe(() => {
            this.matSnackBar.open("Rental deleted successfully !", 'Close', {
                duration: 3000,
            });
            this.rentals = this.rentals.filter((r: Rental) => r.id !== rental.id);
        });
    }
}
