package org.perscholas.controllers;

import org.perscholas.global.GlobalData;
import org.perscholas.model.Book;
import org.perscholas.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    BookService bookService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id){
        GlobalData.cart.add(bookService.getBookById(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Book::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String removeBookFromCart(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
    model.addAttribute("total" ,GlobalData.cart.stream().mapToDouble(Book::getPrice).sum());
    return "checkout";
    }

    @GetMapping("/checkout/payNow")
    public String payment(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Book::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "payment";
    }

}
