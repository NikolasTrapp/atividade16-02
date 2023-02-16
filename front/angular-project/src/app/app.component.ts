import { Component } from '@angular/core';
import { HttpService } from './http.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor (private httpService: HttpService) {}


  title = "";
  date: Date = new Date();

  pessoa = {
    nome: "Nikolas",
    idade: 18
  }

  filmes: string[] = [
    "As aventuras de tintim",
    "Vingadores ultimato",
    "A fuga das galinhas",
    "Shrek 2",
    "Vida de inseto"
  ]

  handleEvent(){
    console.log("d");
    
    this.httpService.getRequest(1).subscribe((response) => console.log(response)
    );
  }
} 
