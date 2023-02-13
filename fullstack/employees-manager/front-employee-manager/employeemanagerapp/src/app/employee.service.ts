import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Employee } from "./employee";
import { HttpClient } from "@angular/common/http";
// import { environment } from "src/envi"


@Injectable({
  providedIn: "root"
})

export class EmployeeService {
  private apiServerUrl = "";

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee`);
  }

  public getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiServerUrl}/employee/find/${id}`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiServerUrl}/employee/add`, employee);
  }

  public deleteEmployee(employeeId: number): void {
    this.http.delete<null>(`${this.apiServerUrl}/employee/delete/${employeeId}`);
  }

  public updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiServerUrl}/employee/update`, employee);
  }
}