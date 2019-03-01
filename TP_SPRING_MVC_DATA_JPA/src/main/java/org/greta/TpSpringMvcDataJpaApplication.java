package org.greta;

import org.greta.dao.ProduitRepository;
import org.greta.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TpSpringMvcDataJpaApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx=SpringApplication.run(TpSpringMvcDataJpaApplication.class, args);
		
		ProduitRepository produitRepository=ctx.getBean(ProduitRepository.class);
		
		/*
		 * produitRepository.save(new Produit("ipmr Assus",120,451));
		 * produitRepository.save(new Produit("ImprEpson",150,12));
		 * produitRepository.save(new Produit("Ordina Hp",140,21));
		 * produitRepository.save(new Produit("Dell 620",170,15));
		 * produitRepository.save(new Produit("Ordinat Hp",140,21));
		 * 
		 * produitRepository.save(new Produit("Souris",155,147));
		 * produitRepository.save(new Produit("Cartouches",101,125));
		 * produitRepository.save(new Produit("Ordinat Hp",189,218));
		 * produitRepository.save(new Produit("Dell 620",190,158));
		 * produitRepository.save(new Produit("Ordinat Hp",1789,21));
		 */
	   
		
		/*
		 * //Expression lambda de java 8
		 * produitRepository.findAll().forEach(p->System.out.println("Designation :"+p.
		 * getDesignation()));
		 */
	}

}

