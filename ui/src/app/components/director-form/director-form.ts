import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { Director } from '../../models/director';

@Component({
  selector: 'app-director-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './director-form.html',
  styleUrl: './director-form.scss',
})
export class DirectorFormComponent implements OnInit {
  director: Partial<Director> = {
    firstName: '',
    lastName: '',
    birthYear: 2004,
  };

  isEditMode = false; // editmode czy nowego dodajemy
  directorId: string | null = null;

  constructor(
    private api: ApiService,
    private router: Router,
    private route: ActivatedRoute // czytanie id z paska
  ) {}

  ngOnInit(): void {
    // czy jest id
    this.directorId = this.route.snapshot.paramMap.get('id');

    if (this.directorId) {
      this.isEditMode = true;
      this.loadDirector(this.directorId);
    }
  }

  loadDirector(id: string): void {
    this.api.getDirector(id).subscribe({
      next: (data) => (this.director = data),
      error: (err) => console.error('Błąd pobierania:', err),
    });
  }

  onSubmit(): void {
    if (this.isEditMode && this.directorId) {
      // AKTUALIZACJA (PUT)
      this.api.updateDirector(this.directorId, this.director).subscribe({
        next: () => this.router.navigate(['/directors']),
        error: (err) => console.error('Błąd aktualizacji:', err),
      });
    } else {
      // TWORZENIE (POST)
      this.api.createDirector(this.director).subscribe({
        next: () => this.router.navigate(['/directors']),
        error: (err) => console.error('Błąd tworzenia:', err),
      });
    }
  }
}
