package bowen.springboot.controllerTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import bowen.springboot.controller.AdController;
import bowen.springboot.service.AdService;


@RunWith(MockitoJUnitRunner.class)
public class AdControllerTest {

	@Mock
	private AdService adService;

	@InjectMocks 
	private AdController adsController;
	
	private List<Ad> mockAds;
	
	private Ad mockAd;
	
	@Mock
	private BindingResult bindingResult;
	
	@Before
	public void setUp() {
		adsController = new AdController();
		mockAds = createMockAds();
		mockAd = createMockAd();
		ReflectionTestUtils.setField(adsController, "adService", adService);
	}
	
	@After
	public void destroy() {
		adsController = null;
	}
	
	private List<Ad> createMockAds() {
		mockAds = new ArrayList<Ad>();
		Ad mockAd = createMockAd();
		mockAds.add(mockAd);
		return mockAds;
	}

	private Ad createMockAd() {
		Ad mockAd = new Ad();
		mockAd.setPartner_id("abd");
		mockAd.setDuration(160);
		mockAd.setAd_content("Try our product!");
		return mockAd;
	}
	
	@Test
	public void retrieveAllAds() {
		
		when(adService.retrieveAllAds()).thenReturn(mockAds);

		List<Ad> ads = adsController.retrieveAllAds();

		Assert.assertEquals(1, ads.size());
		Assert.assertEquals("abd", ads.get(0).getPartner_id());
	}
	
	@Test
	public void retrieveAd() {
		
		when(adService.retrieveAd("abd")).thenReturn(mockAd);

		
		try {
			Ad ad = adsController.retrieveAd("abd");
			Assert.assertNotNull(ad);
			Assert.assertEquals("abd", ad.getPartner_id());
			Assert.assertEquals(60, ad.getDuration());
			Assert.assertEquals("Try our product!", ad.getAd_content());
		} catch(IllegalStateException e) {
			Assert.assertEquals(e.getMessage(), "No active ad campaigns exist for the specified partner");
		}
	}
	
	@Test
	public void createAd() {
        doNothing().when(adService).createAd(mockAd);
		when(bindingResult.hasErrors()).thenReturn(false);

		adsController.createAd(mockAd, bindingResult);
		
		verify(adService, times(1)).createAd(mockAd);
	}
	
	@Test
	public void deleteAd() {
        doNothing().when(adService).deleteAd("abd");

		adsController.deleteAd("abd");
		
		verify(adService, times(1)).deleteAd("abd");
	}

}
