package bowen.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import bowen.springboot.bean.Ad;
import bowen.springboot.service.AdService;


@Service
public class AdServiceImpl implements AdService {
	
	private Map<String, Ad> ads;
	
	@Override
	public void createAd(Ad ad) {
		if(CollectionUtils.isEmpty(ads)){
			ads = new HashMap<String, Ad>();
		}
		ad.setCreationTime(System.currentTimeMillis());
		ads.put(ad.getPartner_id(), ad);
	}
	
	@Override
	public void updateAd(Ad ad) {
		if(CollectionUtils.isEmpty(ads)){
			ads = new HashMap<String, Ad>();
		}
		ad.setCreationTime(System.currentTimeMillis());
		ads.put(ad.getPartner_id(), ad);
	}
	
	@Override
	public List<Ad> retrieveAllAds() {
		
		if(CollectionUtils.isEmpty(ads)) {
			return new ArrayList<Ad>();
		}
		return new ArrayList<Ad>(ads.values());
	}
	
	@Override
	public Ad retrieveAd(String partnerId) {
		Ad ad = null;
		if(!CollectionUtils.isEmpty(ads)){
			ad = ads.get(partnerId);
		}
		
		return ad;
	}

	@Override
	public void deleteAd(String partnerId) {
		
		if(!CollectionUtils.isEmpty(ads)){
			ads.remove(partnerId);
		}
	}
	
	public void setAds(Map<String, Ad> ads) {
		this.ads = ads;
	}

}
