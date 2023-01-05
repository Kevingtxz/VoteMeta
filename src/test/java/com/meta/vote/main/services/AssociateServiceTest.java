package com.meta.vote.main.services;


import com.meta.vote.main.builders.AssociateFormBuilder;
import com.meta.vote.main.dtos.forms.AssociateForm;
import com.meta.vote.main.dtos.mappers.AssociateMapper;
import com.meta.vote.main.entities.AssociateEntity;
import com.meta.vote.main.repositories.AssociateRepository;
import com.meta.vote.main.services.exceptions.ObjectNotFoundException;
import com.meta.vote.main.services.impl.AssociateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociateServiceTest {


    @Mock
    private AssociateRepository repo;
    @InjectMocks
    private AssociateServiceImpl service;
    @Spy
    private AssociateMapper mapper;


    @Test
    void whenInsertIsCalledWithValidFormThenAEntityBeCreated() {
        AssociateForm expectedCreatedForm =
                AssociateFormBuilder.builder().build().toForm();
        AssociateEntity expectedCreatedEntity =
                mapper.toEntity(expectedCreatedForm);
        expectedCreatedEntity.setId(null);

        when(this.repo.save(expectedCreatedEntity))
                .thenReturn(expectedCreatedEntity);

        this.service.insert(expectedCreatedForm);

        verify(this.repo, times(1))
                .save(expectedCreatedEntity);
    }
    @Test
    void whenDeleteIsCalledWithValidIdThenAEntityBeDeleted()
            throws ObjectNotFoundException {
        AssociateForm expectedDeletedForm =
                AssociateFormBuilder.builder().build().toForm();
        AssociateEntity expectedDeletedEntity =
                this.mapper.toEntity(expectedDeletedForm);

        when(this.repo.findById(expectedDeletedForm.getId()))
                .thenReturn(Optional.of(expectedDeletedEntity));
        doNothing().when(this.repo).delete(expectedDeletedEntity);

        this.service.delete(expectedDeletedForm.getId());

        verify(this.repo, times(1))
                .findById(expectedDeletedForm.getId());
        verify(this.repo, times(1))
                .delete(expectedDeletedEntity);
    }
//    @Test
//    void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
//        // given
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedSavedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        // when
//        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
//        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);
//
//        //then
//        BeerDTO createdBeerDTO = beerService.createBeer(expectedBeerDTO);
//
//        assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId())));
//        assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName())));
//        assertThat(createdBeerDTO.getQuantity(), is(equalTo(expectedBeerDTO.getQuantity())));
//    }
//
//    @Test
//    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
//        // given
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer duplicatedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        // when
//        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));
//
//        // then
//        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));
//    }
//
//    @Test
//    void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {
//        // given
//        BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);
//
//        // when
//        when(beerRepository.findByName(expectedFoundBeer.getName())).thenReturn(Optional.of(expectedFoundBeer));
//
//        // then
//        BeerDTO foundBeerDTO = beerService.findByName(expectedFoundBeerDTO.getName());
//
//        assertThat(foundBeerDTO, is(equalTo(expectedFoundBeerDTO)));
//    }
//
//    @Test
//    void whenNotRegisteredBeerNameIsGivenThenThrowAnException() {
//        // given
//        BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//
//        // when
//        when(beerRepository.findByName(expectedFoundBeerDTO.getName())).thenReturn(Optional.empty());
//
//        // then
//        assertThrows(BeerNotFoundException.class, () -> beerService.findByName(expectedFoundBeerDTO.getName()));
//    }
//
//    @Test
//    void whenListBeerIsCalledThenReturnAListOfBeers() {
//        // given
//        BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);
//
//        //when
//        when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));
//
//        //then
//        List<BeerDTO> foundListBeersDTO = beerService.listAll();
//
//        assertThat(foundListBeersDTO, is(not(empty())));
//        assertThat(foundListBeersDTO.get(0), is(equalTo(expectedFoundBeerDTO)));
//    }
//
//    @Test
//    void whenListBeerIsCalledThenReturnAnEmptyListOfBeers() {
//        //when
//        when(beerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
//
//        //then
//        List<BeerDTO> foundListBeersDTO = beerService.listAll();
//
//        assertThat(foundListBeersDTO, is(empty()));
//    }
//
//    @Test
//    void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted() throws BeerNotFoundException{
//        // given
//        BeerDTO expectedDeletedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedDeletedBeer = beerMapper.toModel(expectedDeletedBeerDTO);
//
//        // when
//        when(beerRepository.findById(expectedDeletedBeerDTO.getId())).thenReturn(Optional.of(expectedDeletedBeer));
//        doNothing().when(beerRepository).deleteById(expectedDeletedBeerDTO.getId());
//
//        // then
//        beerService.deleteById(expectedDeletedBeerDTO.getId());
//
//        verify(beerRepository, times(1)).findById(expectedDeletedBeerDTO.getId());
//        verify(beerRepository, times(1)).deleteById(expectedDeletedBeerDTO.getId());
//    }
//
//    @Test
//    void whenIncrementIsCalledThenIncrementBeerStock() throws BeerNotFoundException, BeerStockExceededException {
//        //given
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        //when
//        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
//        when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);
//
//        int quantityToIncrement = 10;
//        int expectedQuantityAfterIncrement = expectedBeerDTO.getQuantity() + quantityToIncrement;
//
//        // then
//        BeerDTO incrementedBeerDTO = beerService.increment(expectedBeerDTO.getId(), quantityToIncrement);
//
//        assertThat(expectedQuantityAfterIncrement, equalTo(incrementedBeerDTO.getQuantity()));
//        assertThat(expectedQuantityAfterIncrement, lessThan(expectedBeerDTO.getMax()));
//    }
//
//    @Test
//    void whenIncrementIsGreatherThanMaxThenThrowException() {
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
//
//        int quantityToIncrement = 80;
//        assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement));
//    }
//
//    @Test
//    void whenIncrementAfterSumIsGreatherThanMaxThenThrowException() {
//        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
//        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
//
//        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
//
//        int quantityToIncrement = 45;
//        assertThrows(BeerStockExceededException.class, () -> beerService.increment(expectedBeerDTO.getId(), quantityToIncrement));
//    }
//
//    @Test
//    void whenIncrementIsCalledWithInvalidIdThenThrowException() {
//        int quantityToIncrement = 10;
//
//        when(beerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty());
//
//        assertThrows(BeerNotFoundException.class, () -> beerService.increment(INVALID_BEER_ID, quantityToIncrement));
//    }
////
////    @Test
////    void whenDecrementIsCalledThenDecrementBeerStock() throws BeerNotFoundException, BeerStockExceededException {
////        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
////        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
////
////        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
////        when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);
////
////        int quantityToDecrement = 5;
////        int expectedQuantityAfterDecrement = expectedBeerDTO.getQuantity() - quantityToDecrement;
////        BeerDTO incrementedBeerDTO = beerService.decrement(expectedBeerDTO.getId(), quantityToDecrement);
////
////        assertThat(expectedQuantityAfterDecrement, equalTo(incrementedBeerDTO.getQuantity()));
////        assertThat(expectedQuantityAfterDecrement, greaterThan(0));
////    }
////
////    @Test
////    void whenDecrementIsCalledToEmptyStockThenEmptyBeerStock() throws BeerNotFoundException, BeerStockExceededException {
////        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
////        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
////
////        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
////        when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);
////
////        int quantityToDecrement = 10;
////        int expectedQuantityAfterDecrement = expectedBeerDTO.getQuantity() - quantityToDecrement;
////        BeerDTO incrementedBeerDTO = beerService.decrement(expectedBeerDTO.getId(), quantityToDecrement);
////
////        assertThat(expectedQuantityAfterDecrement, equalTo(0));
////        assertThat(expectedQuantityAfterDecrement, equalTo(incrementedBeerDTO.getQuantity()));
////    }
////
////    @Test
////    void whenDecrementIsLowerThanZeroThenThrowException() {
////        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
////        Beer expectedBeer = beerMapper.toModel(expectedBeerDTO);
////
////        when(beerRepository.findById(expectedBeerDTO.getId())).thenReturn(Optional.of(expectedBeer));
////
////        int quantityToDecrement = 80;
////        assertThrows(BeerStockExceededException.class, () -> beerService.decrement(expectedBeerDTO.getId(), quantityToDecrement));
////    }
////
////    @Test
////    void whenDecrementIsCalledWithInvalidIdThenThrowException() {
////        int quantityToDecrement = 10;
////
////        when(beerRepository.findById(INVALID_BEER_ID)).thenReturn(Optional.empty());
////
////        assertThrows(BeerNotFoundException.class, () -> beerService.decrement(INVALID_BEER_ID, quantityToDecrement));
////    }

}
