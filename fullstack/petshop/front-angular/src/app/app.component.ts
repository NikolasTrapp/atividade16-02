import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Owner } from './models/owner';
import { Pet } from './models/pet';
import { Register } from './models/register';
import { PetshopService } from './petshop.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  pets: Pet[] = [];
  registers: Register[] = [];
  owners: Owner[] = [];
  editPet?: Pet;

  constructor(private petshopService: PetshopService) { }

  ngOnInit(): void {
    this.petshopService.getPets().subscribe(
      (response) => {
        this.pets = response;
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
    this.petshopService.getOwners().subscribe(
      (response) => {
        this.owners = response;
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }

  updatePetId(id: number | string): void {
    this.petshopService.getPetById(id).subscribe(
      (response) => {
        this.editPet = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      });
  }

  updatePet(ngForm: NgForm): void{
    ngForm.value.owner = {"id": parseInt(ngForm.value.owner)};
    this.petshopService.updatePet(ngForm.value, this.editPet!.id).subscribe((response) => {
      alert("Pet updated");
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  addRegister(ngForm: NgForm): void {
    this.petshopService.postRegister(ngForm.value).subscribe((response) => {
      alert("Register added to system!");
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  addPet(ngForm: NgForm): void {
    this.petshopService.postPet(ngForm.value).subscribe((response) => {
      alert(response.name + " added to system!");
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  removePet(id: number | string): void {
    this.petshopService.deletePet(id).subscribe((response) => {
      alert("Pet removed from system!");
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  addOwner(ngForm: NgForm): void {
    console.log(ngForm.value);

    this.petshopService.postOwner(ngForm.value).subscribe((response) => {
      alert(response.name + " added to system!");
    }, (error: HttpErrorResponse) => {
      alert(error.message);
    });

  }

  queryRegisters(id: number | string): void {
    this.petshopService.getRegistersByPetId(id).subscribe(
      (response) => {
        this.registers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }
}
