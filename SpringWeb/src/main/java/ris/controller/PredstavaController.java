package ris.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Izvodjenje;
import model.Karta;
import model.Mesto;
import model.Posetilac;
import model.Predstava;
import model.Reziser;
import model.Zanr;
import ris.repository.IzvodjenjeRepository;
import ris.repository.KartaRepository;
import ris.repository.MestoRepository;
import ris.repository.PosetilacRepository;
import ris.repository.PredstavaRepository;
import ris.repository.ReziserRepository;
import ris.repository.ZanrRepository;

@Controller
@RequestMapping(value = "/predstavaController")
public class PredstavaController {
	
	@Autowired
	ZanrRepository zr;
	
	@Autowired
	ReziserRepository rr;
	
	@Autowired
	PredstavaRepository pr;
	
	@Autowired
	IzvodjenjeRepository ir;
	
	@Autowired
	MestoRepository mr;
	
	@Autowired
	PosetilacRepository posrep;
	
	@Autowired
	KartaRepository kr;
	
	@RequestMapping(value = "/getZanroviReziser", method = RequestMethod.GET)
	public String getZanrRez(HttpServletRequest request) {
		
		List<Zanr> zanrovi = zr.findAll();
		List<Reziser> reziseri = rr.findAll();
		
		if(zanrovi != null && reziseri != null) {
			
			request.getSession().setAttribute("zanrovi", zanrovi);
			request.getSession().setAttribute("reziseri", reziseri);
			
			return "unosPredstave";
			
		}
		
		return "index";
		
	}
	
	@RequestMapping(value = "/savePredstava", method = RequestMethod.POST)
	public String sacuvajPredstavu(String naziv, String opis, String trajanje, Integer zanr, Integer reziser, 
								   HttpServletRequest request) {
		
		Integer trajanjeP = Integer.parseInt(trajanje);
		
		Reziser r = rr.findById(reziser).get();
		Zanr z = zr.findById(zanr).get();
		
		Predstava p = new Predstava();
		
		p.setReziser(r);
		p.setNaziv(naziv);
		p.setOpis(opis);
		p.setTrajanje(trajanjeP);
		p.setZanr(z);
		
		Predstava pre = pr.save(p);
		
		request.getSession().setAttribute("predstava", pre);
		
		return "unosPredstave";
		
		
	}
	
	@RequestMapping(value = "/getIzvodjenja", method = RequestMethod.GET)
	public String getIzvodjenja(String nazPredstave, HttpServletRequest request) {
		
		List<Izvodjenje> lista = ir.findIzvodjenja(nazPredstave);
		
		request.getSession().setAttribute("izvodjenja", lista);
		
		return "izvodjenjaPredstave";
		
		
	}
	
	@RequestMapping(value = "/vratiMesta", method = RequestMethod.GET)
	public String rezervacija(String id, HttpServletRequest request) {
		
		Integer idI = Integer.parseInt(id);
		
		List<Mesto> mesta = mr.slobodnaMesta(idI);
		
		request.getSession().setAttribute("mesta", mesta);
		request.getSession().setAttribute("id", idI);
		
		return "rezervacija";
		
	}
	
	@RequestMapping(value = "/sacuvajKartu", method = RequestMethod.POST)
	public String saveKarta(Integer mesto, String idPosetilac, HttpServletRequest request) {
		
		Integer idIzvodjenje = (Integer) request.getSession().getAttribute("id");
		Izvodjenje i = ir.findById(idIzvodjenje).get();
		
		Mesto m = mr.findById(mesto).get();
		
		Integer idPos = Integer.parseInt(idPosetilac);
		Posetilac p = posrep.findById(idPos).get();
		
		Karta k = new Karta();
		
		k.setIzvodjenje(i);
		k.setMesto(m);
		k.setPosetilac(p);
		k.setCena(5000);
		
		Karta karta = kr.save(k);
		
		request.getSession().setAttribute("karta", karta);
		
		return "rezervacija";
		
	}
	
	@RequestMapping(value = "/getReziser", method = RequestMethod.GET)
	public String getReziseri(HttpServletRequest request) {
		
		List<Reziser> listaR = rr.findAll();
		
		request.getSession().setAttribute("reziseri", listaR);
		
		return "prikazRezisera";
	}
	
	@RequestMapping(value = "/getPredstaveZaRezisera", method = RequestMethod.GET)
	public String getPredstaveZaRezisera(HttpServletRequest request, Integer reziser) {
		
		Reziser r = rr.findById(reziser).get();
		List<Predstava> lista = pr.findByReziser(r);
		
		request.getSession().setAttribute("predstaveR", lista);
		
		return "prikazRezisera";
	}
	
	
	
	

}
