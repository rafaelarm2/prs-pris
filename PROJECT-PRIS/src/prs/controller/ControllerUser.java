package prs.controller;

import prs.controller.entity.User;
import prs.data.IRepositoryUser;

public class ControllerUser {
	private IRepositoryUser rUser;

	public ControllerUser(IRepositoryUser rUser) {
		this.rUser = rUser;
	}

	public boolean create(User user) {
		return rUser.create(user);
	}

	public boolean isValid(User user) {
		return rUser.isValid(user);
	}

	
	
}
