package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


    @Controller

    public class UserController {
        private UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }


        @GetMapping(value = "/")
        public String viewUsers(Model model){
            model.addAttribute("users",userService.getUsersList());
            return "users";
        }

        @GetMapping("/new")
        public String getNewUserForm(Model model) {
            model.addAttribute("user", new User());
            return "new";
        }

        @PostMapping("/new")
        public String addNewUser(@ModelAttribute("user") User user){
            userService.addUser(user);
            return "redirect:/";
        }

        @GetMapping("/users/{id}")
        public String getFormUserUpdate(Model model,@PathVariable("id") int id){
            model.addAttribute("user",userService.getUserById(id));
            return "edit";
        }

        @PatchMapping("/users/{id}")
        public String updateUser (@ModelAttribute("user") User user,@PathVariable("id")int id){
            userService.updateUser(id,user);
            return "redirect:/";
        }

        @DeleteMapping("/users/{id}")
        public String deleteUser(@PathVariable("id") int id){
            userService.deleteUser(id);
            return "redirect:/";
        }
}
