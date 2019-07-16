export class User {

  username: string;
  email: string;
  password: string;
  confirmPassword: string;
  role: string;


  constructor(username: string, email: string, password: string, confirmPassword: string, role: string) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.role = role;
  }

}
