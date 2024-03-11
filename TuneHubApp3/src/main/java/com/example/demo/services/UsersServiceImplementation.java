package com.example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;
@Service
public class UsersServiceImplementation implements UsersService{
	@Autowired
	UsersRepository repo;
	public String addUser(Users user)
	{
		repo.save(user);
		return "user is created and saved";
	}
	@Override
	public boolean emailExists(String email) 
	{
		if(repo.findByEmail(email)==null) {
			return false;
		}
		else {
			return true;
		}
	}
	@Override
	public boolean validateUser(String email, String Password) 
	{
		Users user=(Users) repo.findByEmail(email);
		String db_password=user.getPassword();
		if(db_password.equals(Password)) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public String getRole(String email) 
	{
		Users user=(Users)repo.findByEmail(email);
		String role=user.getRole();
		return role;
	}
	@Override
	public Users getUser(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}
	@Override
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		repo.save(user);
		
	}
	

}
