package prs.data;

import prs.controller.entity.User;

public interface IRepositoryUser {
	
	boolean create(User user);

	boolean isValid(User user);

}
