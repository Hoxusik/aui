import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ApiService } from '../../service/api.service';
import { Director } from '../../models/director';

@Component({
  selector: 'app-director-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  // UWAGA: Tu są nazwy Twoich plików (bez ".component")
  templateUrl: './director-list.html',
  styleUrl: './director-list.scss',
})
export class DirectorListComponent implements OnInit {
  directors: Director[] = [];

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.fetchDirectors();
  }

  fetchDirectors(): void {
    this.api.getDirectors().subscribe({
      next: (data) => {
        this.directors = data;
        console.log('Pobrano reżyserów:', data);
      },
      error: (err) => console.error('Błąd pobierania:', err),
    });
  }

  deleteDirector(id: string): void {
    if (confirm('Czy na pewno chcesz usunąć tego reżysera?')) {
      this.api.deleteDirector(id).subscribe(() => {
        this.fetchDirectors(); // Odśwież listę po usunięciu
      });
    }
  }
}
