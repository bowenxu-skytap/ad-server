package bowen.springboot.serviceImplTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;

import bowen.springboot.bean.Ad;
import bowen.springboot.service.AdService;
import bowen.springboot.serviceImpl.AdServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class AdServiceImplTest {

	private static final String PARTNER_ID_ABC = "abc";

	@Mock
	private AdService adService;

	@InjectMocks 
	private AdServiceImpl adServiceImpl;

	private List<Ad> mockAds;

	private Ad mockAd;

	private Map<String, Ad> ads;

	@Mock
	private BindingResult bindingResult;

	@Before
	public void setUp() {
		adServiceImpl = new AdServiceImpl();
		mockAds = createMockAds();
		mockAd = createMockAd();
		ReflectionTestUtils.setField(adServiceImpl, "ads", ads);
	}

	@After
	public void destroy() {
		adServiceImpl = null;
	}

	private List<Ad> createMockAds() {
		mockAds = new ArrayList<Ad>();
		Ad mockAd = createMockAd();
		mockAds.add(mockAd);
		return mockAds;
	}

	private Ad createMockAd() {
		Ad mockAd = new Ad();
		mockAd.setPartner_id(PARTNER_ID_ABC);
		mockAd.setDuration(60);
		mockAd.setAd_content("Try our product!");
		return mockAd;
	}

	@Test
	public void retrieveAllAds() {
		
		Assert.assertNull(ads);

		addAnAd(); 

		List<Ad> ads = adServiceImpl.retrieveAllAds();

		Assert.assertEquals(1, ads.size());
		Assert.assertEquals(PARTNER_ID_ABC, ads.get(0).getPartner_id());
	}

	@Test
	public void retrieveAd() {

		Assert.assertNull(ads);

		addAnAd(); 

		Ad ad = adServiceImpl.retrieveAd(PARTNER_ID_ABC);

		Assert.assertNotNull(ad);
		Assert.assertEquals(PARTNER_ID_ABC, ad.getPartner_id());
		Assert.assertEquals(60, ad.getDuration());
		Assert.assertEquals("Try our product!", ad.getAd_content());
	}

	@Test
	public void createAd() {
		Assert.assertNull(ads);

		adServiceImpl.createAd(mockAd);
	}

	@Test
	public void deleteAd() {

		Assert.assertNull(ads);

		addAnAd(); 

		adServiceImpl.deleteAd(PARTNER_ID_ABC);

		Assert.assertEquals(0, ads.size());
	}

	private void addAnAd() {
		// Add an ad to remove
		ads = new HashMap<String, Ad>();
		ads.put(PARTNER_ID_ABC, mockAd);

		Assert.assertNotNull(ads);
		Assert.assertEquals(1, ads.size());
		adServiceImpl.setAds(ads);
	}

}
