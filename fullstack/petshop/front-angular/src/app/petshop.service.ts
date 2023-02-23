import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Pet } from './models/pet';
import { Owner } from './models/owner';
import { Register } from './models/register';

@Injectable({
  providedIn: 'root'
})
export class PetshopService {

  private url = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getPets(): Observable<Pet[]>{
    return this.http.get<Pet[]>(`${this.url}/pet`);
  }

  public getOwners(): Observable<Owner[]>{
    return this.http.get<Owner[]>(`${this.url}/owner`);
  }

  public getRegisters(): Observable<Register[]>{
    return this.http.get<Register[]>(`${this.url}/register`);
  }

  public getRegistersByPetId(id: number | string): Observable<Register[]>{
    return this.http.get<Register[]>(`${this.url}/register/findByPetId/${id}`);
  }

  public getPetById(id: number | string): Observable<Pet>{
    return this.http.get<Pet>(`${this.url}/pet/${id}`);
  }

  public postPet(pet: Pet): Observable<Pet>{
    return this.http.post<Pet>(`${this.url}/pet`, pet);
  }

  public postOwner(owner: Owner): Observable<Owner>{
    return this.http.post<Owner>(`${this.url}/owner`, owner);
  }

  public postRegister(register: Register): Observable<Register>{
    return this.http.post<Register>(`${this.url}/register`, register);
  }

  public deletePet(id: number | string) : Observable<void>{
    return this.http.delete<void>(`${this.url}/pet/${id}`);
  }

  public deleteOwner(id: number | string) : Observable<void>{
    return this.http.delete<void>(`${this.url}/owner/${id}`);
  }

  public deleteRegister(id: number | string) : Observable<void>{
    return this.http.delete<void>(`${this.url}/register/${id}`);
  }

  public updatePet(pet: Pet, id: number | string) : Observable<Pet>{
    return this.http.put<Pet>(`${this.url}/pet/${id}`, pet);
  }

  public updateOwner(owner: Owner, id: number | string) : Observable<Owner>{
    return this.http.put<Owner>(`${this.url}/owner/${id}`, owner);
  }

  public updateRegister(register: Register, id: number | string) : Observable<Register>{
    return this.http.put<Register>(`${this.url}/register/${id}`, register);
  }
}
