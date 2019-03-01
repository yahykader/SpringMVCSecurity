package org.greta.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.greta.dao.ProduitRepository;
import org.greta.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// Class controller spring MVC posede l'annotaion suivante:
@SuppressWarnings("unused")
@Controller
public class ProduitController {
	
	//faire aplle a la couche dao,pour faire l'injection des dependances posede l'annotation suivante
	
	@Autowired
	private ProduitRepository produitrepository;
	// il reponds que a index dans l'url
	@RequestMapping(value="/user/index")
	// il faut recuperer le resultat et stocker dans le model
	//Appel le model est un objet de type Model qui implemente l'interface Maps
	//
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="size",defaultValue="5")int s,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		
		//recupere le liste de produits de couche metier
		// faire la pagenation 
		@SuppressWarnings("deprecation")
		Page<Produit> pageProduits=produitrepository.chercher("%"+mc+"%",new PageRequest(p,s));
		
		// socker dans le model par addAttribute(cle,valeur)
		
		model.addAttribute("listeProduits",pageProduits.getContent());
		//affiche le lien dans la page
		int[]pages=new int [pageProduits.getTotalPages()];
		
		model.addAttribute("pages",pages);
		model.addAttribute("size",s);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		// return le nom de la vue produits.html
		return "produits";
	}
	
	@RequestMapping(value="/admin/delete",method=RequestMethod.GET)
	public String delete(Long id,String motCle, int page,int size) {
		produitrepository.deleteById(id);
		return "redirect:/user/index?page="+page+"&size="+size+"&motCle="+motCle;
		
	}
	
	@RequestMapping(value="/admin/form",method=RequestMethod.GET)
	public String formProduit(Model model) {
		model.addAttribute("produit",new Produit());
		return "formProduit";
	}
	@RequestMapping(value="/admin/edit",method=RequestMethod.GET)
	public String edit(Model model,Long id) {
		Produit p=new Produit();
		produitrepository.findById(id);
		model.addAttribute("produit",p);
		return "editProduit";
	}
	
	@RequestMapping(value="/admin/save",method=RequestMethod.POST)
	public String formProduit(Model model,@Valid Produit produit,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "formProduit";
		produitrepository.save(produit);
		//model.addAttribute("produit",new Produit());
		return "confirmation";
	}
	
	
	  @RequestMapping(value="/user/update",method=RequestMethod.POST) public String
	  editProduit(Model model,@Valid Produit produit,BindingResult bindingResult) {
	  
	  if(bindingResult.hasErrors()) return "editProduit";
	  produitrepository.save(produit); //model.addAttribute("produit",newProduit());
	  return "confirmation"; }
	 
	
	@RequestMapping(value="/")
	public String home() {
		
		return "redirect:/user/index";
	}
	
	@RequestMapping(value="/403") 
	public String accessDneied() {
	  
	  return "403"; 
	  
	}
	
	 @RequestMapping(value="/login")
	 public String login() {
		 
		  return "login"; 
		  
	 }
	 
	 
}


