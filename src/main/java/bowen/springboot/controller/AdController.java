package bowen.springboot.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bowen.springboot.bean.Ad;
import bowen.springboot.service.AdService;


@RestController
public class AdController {
	
	@Autowired
	private AdService adService;
	
	
	/**
	 * Retrieves all existing ads.
	 * 
	 * @return List of Ads
	 */
	@GetMapping("/ad")
	public List<Ad> retrieveAllAds() {
		return adService.retrieveAllAds();
	}
	
	
	/**
	 * Retrieves Ad for a given partner id.
	 * 
	 * @param partner_id Unique id of a partner
	 * @return	One entry of Ad
	 */
	@GetMapping("/ad/{partner_id}")
	public Ad retrieveAd(@PathVariable("partner_id") String partner_id) throws IllegalStateException{
		Ad ad = adService.retrieveAd(partner_id);
		if(System.currentTimeMillis() > ad.getCreationTime() + TimeUnit.SECONDS.toMillis(ad.getDuration())) {
			throw new IllegalStateException("No active ad campaigns exist for the specified partner");
		}
		return ad;
	}
	
	
	/**
	 * Creates a new Ad.
	 * 
	 * @param ad	One entry of Ad
	 * @param result	Stores any input validation errors.
	 */
	@PostMapping("/ad")
	public void createAd(@RequestBody @Valid Ad ad, BindingResult result) {
		if(result.hasErrors()) {
			throw new IllegalStateException("Input Validation Error Occured"); // TODO - Create a custom exception
		}
		adService.createAd(ad);
	}
	
	/**
	 * Updates an existing Ad.
	 * 
	 * @param ad
	 */
	@PutMapping("/ad")
	public void updateAd(@RequestBody Ad ad) {
		adService.updateAd(ad);
	}
	
	
	/**
	 * Deletes an existing Ad.
	 * 
	 * @param partner_id	Unique id of a partner
	 */
	@DeleteMapping("/ad/{partner_id}")
	public void deleteAd(@PathVariable("partner_id") String partner_id) {
		adService.deleteAd(partner_id);
	}
	
}

