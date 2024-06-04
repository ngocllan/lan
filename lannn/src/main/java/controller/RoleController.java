package controller;

import model.Role;
import model.User;
import service.RoleService;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("/roles")
    public String getAllRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "role_list";
    }

    @GetMapping("/roles/create")
    public String createRoleForm(Model model) {
        model.addAttribute("role", new Role());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "role_form";
    }

    @PostMapping("/roles/save")
    public String saveRole(@ModelAttribute Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/roles/edit/{id}")
    public String editRoleForm(@PathVariable Long id, Model model) {
        Role role = roleService.findById(id).orElse(null);
        model.addAttribute("role", role);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "role_form";
    }

    @GetMapping("/roles/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/roles";
    }
}
