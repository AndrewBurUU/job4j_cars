package ru.job4j.cars.controller;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    private final EngineService engineService;

    private final CarService carService;

    private final FileService fileService;

    @GetMapping("/all")
    public String getAll(Model model) {
        var postAll = postService.findAll();
        model.addAttribute("posts", postAll);
        return "posts/all";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("cars", carService.findAll());
        return "posts/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post, Model model, HttpServletRequest req, @RequestParam MultipartFile file) {
        User user = new User();
        user.setId(1);
        post.setUser(user);
        String engineId = req.getParameter("engineId");
        Engine engine = new Engine();
        engine.setId(Integer.parseInt(engineId));
        Car car = new Car();
        car.setId(post.getCar().getId());
        car.setEngine(engine);
        post.setCar(car);
        File carFile = new File();
        carFile.setPath(file.getOriginalFilename());
        carFile.setName(req.getParameter("foto"));

        try {
            post = postService.save(post);
            carFile.setPost(post);
            fileService.save(carFile);
            return "redirect:/posts/all";
        } catch (Exception e) {
            model.addAttribute("message", String.format("Ошибка сохранения: %s", e.getMessage()));
            return "errors/404";
        }
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var postOptional = postService.findById(id);
        if (postOptional.isEmpty()) {
            model.addAttribute("message", "Объявление не найдено.");
            return "errors/404";
        }
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("cars", carService.findAll());
        model.addAttribute("post", postOptional.get());
        return "posts/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post, Model model, HttpServletRequest req,
                         @RequestParam MultipartFile file) {
        try {
            postService.update(post);

            List<File> files = postService.findById(post.getId()).get().getFiles();
            File carFile = new File();
            carFile.setPath(file.getOriginalFilename());
            carFile.setName(req.getParameter("foto"));
            carFile.setPost(post);

            boolean isUpdated = false;
            if (files.isEmpty()) {
                fileService.save(carFile);
            } else {
                carFile.setId(files.get(0).getId());
                isUpdated = fileService.update(carFile);
            }
            if (!isUpdated) {
                model.addAttribute("message", "Ошибка обновления файла.");
                return "errors/404";
            }

            return "redirect:/posts/all";
        } catch (Exception e) {
            model.addAttribute("message", String.format("Ошибка обновления: %s", e.getMessage()));
            return "errors/404";
        }
    }
}
