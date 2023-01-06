package com.meta.vote.main.controllers;

import com.meta.vote.main.dtos.mappers.AssociateMapper;
import com.meta.vote.main.services.AssociateService;
import com.meta.vote.main.services.impl.AssociateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AssociateControllerTest {

    private static final String API_URL_PATH = "/api/v1/associates";
//    private static final int VALID_ID = 1;
//    private static final int INVALID_ID = 2;
    private MockMvc mockMvc;
    @Mock
    private AssociateService service;
    @InjectMocks
    private AssociateController controller;
    @Spy
    private AssociateMapper mapper;


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }
    @Test
    void whenGetIsCalledThenAListOfAssociateEntityIsReturned() throws Exception {
        this.mockMvc.perform(get(this.API_URL_PATH))
                .andExpect(status().isOk());
        verify(this.service, times(1))
                .findAllView();
    }
//    @Test
//    void whenPostIsCalledThenAAssociateIsCreated() throws Exception {
//        AssociateForm expectedCreatedForm =
//                AssociateFormBuilder.builder().build().toForm();
////        AssociateEntity expectedCreatedEntity =
////                this.mapper.toEntity(expectedCreatedForm);
//
//        this.mockMvc.perform(post(this.API_URL_PATH)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(expectedCreatedForm)))
//                .andExpect(status().isCreated());
//
////        verify(this.service, times(1))
////                .insert(expectedCreatedForm);
//    }
//    @Test
//    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
//        // given
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        beerDTO.setBrand(null);
//
//        // then
//        mockMvc.perform(post(BEER_API_URL_PATH)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(beerDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {
//        // given
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//
//        //when
//        when(beerService.findByName(beerDTO.getName())).thenReturn(beerDTO);
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH + "/" + beerDTO.getName())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(beerDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(beerDTO.getType().toString())));
//    }
//
//    @Test
//    void whenGETIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {
//        // given
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//
//        //when
//        when(beerService.findByName(beerDTO.getName())).thenThrow(BeerNotFoundException.class);
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH + "/" + beerDTO.getName())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void whenGETListWithBeersIsCalledThenOkStatusIsReturned() throws Exception {
//        // given
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//
//        //when
//        when(beerService.listAll()).thenReturn(Collections.singletonList(beerDTO));
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is(beerDTO.getName())))
//                .andExpect(jsonPath("$[0].brand", is(beerDTO.getBrand())))
//                .andExpect(jsonPath("$[0].type", is(beerDTO.getType().toString())));
//    }
//
//    @Test
//    void whenGETListWithoutBeersIsCalledThenOkStatusIsReturned() throws Exception {
//        // given
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//
//        //when
//        when(beerService.listAll()).thenReturn(Collections.singletonList(beerDTO));
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
//        // given
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//
//        //when
//        doNothing().when(beerService).deleteById(beerDTO.getId());
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.delete(BEER_API_URL_PATH + "/" + beerDTO.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
//        //when
//        doThrow(BeerNotFoundException.class).when(beerService).deleteById(INVALID_BEER_ID);
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.delete(BEER_API_URL_PATH + "/" + INVALID_BEER_ID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void whenPATCHIsCalledToIncrementDiscountThenOKstatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(10)
//                .build();
//
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(beerService.increment(VALID_BEER_ID, quantityDTO.getQuantity())).thenReturn(beerDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.patch(BEER_API_URL_PATH + "/" + VALID_BEER_ID + BEER_API_SUBPATH_INCREMENT_URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(quantityDTO))).andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(beerDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(beerDTO.getType().toString())))
//                .andExpect(jsonPath("$.quantity", is(beerDTO.getQuantity())));
//    }
//
//    @Test
//    void whenPATCHIsCalledToIncrementGreatherThanMaxThenBadRequestStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(30)
//                .build();
//
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(beerService.increment(VALID_BEER_ID, quantityDTO.getQuantity())).thenThrow(BeerStockExceededException.class);
//
//        mockMvc.perform(patch(BEER_API_URL_PATH + "/" + VALID_BEER_ID + BEER_API_SUBPATH_INCREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .con(asJsonString(quantityDTO))).andExpect(status().isBadRequest());
//    }

//    @Test
//    void whenPATCHIsCalledWithInvalidBeerIdToIncrementThenNotFoundStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(30)
//                .build();
//
//        when(beerService.increment(INVALID_BEER_ID, quantityDTO.getQuantity())).thenThrow(BeerNotFoundException.class);
//        mockMvc.perform(patch(BEER_API_URL_PATH + "/" + INVALID_BEER_ID + BEER_API_SUBPATH_INCREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void whenPATCHIsCalledToDecrementDiscountThenOKstatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(5)
//                .build();
//
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(beerService.decrement(VALID_BEER_ID, quantityDTO.getQuantity())).thenReturn(beerDTO);
//
//        mockMvc.perform(patch(BEER_API_URL_PATH + "/" + VALID_BEER_ID + BEER_API_SUBPATH_DECREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO))).andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(beerDTO.getName())))
//                .andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
//                .andExpect(jsonPath("$.type", is(beerDTO.getType().toString())))
//                .andExpect(jsonPath("$.quantity", is(beerDTO.getQuantity())));
//    }
//
//    @Test
//    void whenPATCHIsCalledToDEcrementLowerThanZeroThenBadRequestStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(60)
//                .build();
//
//        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        beerDTO.setQuantity(beerDTO.getQuantity() + quantityDTO.getQuantity());
//
//        when(beerService.decrement(VALID_BEER_ID, quantityDTO.getQuantity())).thenThrow(BeerStockExceededException.class);
//
//        mockMvc.perform(patch(BEER_API_URL_PATH + "/" + VALID_BEER_ID + BEER_API_SUBPATH_DECREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO))).andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void whenPATCHIsCalledWithInvalidBeerIdToDecrementThenNotFoundStatusIsReturned() throws Exception {
//        QuantityDTO quantityDTO = QuantityDTO.builder()
//                .quantity(5)
//                .build();
//
//        when(beerService.decrement(INVALID_BEER_ID, quantityDTO.getQuantity())).thenThrow(BeerNotFoundException.class);
//        mockMvc.perform(patch(BEER_API_URL_PATH + "/" + INVALID_BEER_ID + BEER_API_SUBPATH_DECREMENT_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(quantityDTO)))
//                .andExpect(status().isNotFound());
//    }
}