package com.ensta.myfilmlist;

import java.util.*;
import java.sql.Date;

import com.ensta.myfilmlist.dao.FilmDAO;
import com.ensta.myfilmlist.dao.RealisateurDAO;
import com.ensta.myfilmlist.service.FilmService;
import com.ensta.myfilmlist.service.RealisateurService;
import com.ensta.myfilmlist.pojo.FilmPojo;
import com.ensta.myfilmlist.pojo.RealisateurPojo;
import com.ensta.myfilmlist.dto.FilmDTO;
import com.ensta.myfilmlist.exception.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmServiceTest {
    @Autowired
    FilmService filmService;
    @Autowired
    RealisateurService realisateurService;
    @MockBean
    FilmDAO filmDAO;
    @MockBean
    RealisateurDAO realisateurDAO;

    /**
     * Test que la fonction findAll de FilmService renvoie bien une liste de FilmDTO à partir d'une liste de FilmPojo qui correspond aux attentes
     * @throws DaoException
     * @throws ServiceException
     */
    @Test
    public void findAllTest() throws DaoException , ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film1 = new FilmPojo(1,"1",1,1);
        List<FilmDTO> listFilmResult = new ArrayList<>();
        FilmDTO filmResult1 = new FilmDTO(1,"1",1,1," 1");
        List<RealisateurPojo> listReal = new ArrayList<>();
        Date date = new Date(0001,01,01);
        RealisateurPojo real1 = new RealisateurPojo(1,"1","",date);
        int number = 1;
        int size = 10;
        listFilmResult.add(filmResult1);
        listFilm.add(film1);
        listReal.add(real1);
        when(filmDAO.findAll(number,size)).thenReturn(listFilm);
        when(realisateurDAO.findAll()).thenReturn(listReal);
        List<FilmDTO> resultList = filmService.findAll(number,size);
        assertEquals(1, resultList.size());
        assertEquals(filmResult1, resultList.get(0));
    }

    /**
     * Test que la fonction getTotal de FilmService renvoie bien le nombre de pages qui correspond aux attentes (rq on pourrait tester avec plus de 10)
     * @throws DaoException
     * @throws ServiceException
     */
    @Test
    public void getTotalTest() throws DaoException , ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film1 = new FilmPojo(1,"1", 1,1);
        long total = 1;
        int size = 10;
        listFilm.add(film1);
        when(filmDAO.countFilms()).thenReturn(listFilm.size());
        long result = filmService.getTotal(size);
        assertEquals((long) 1,result);
    }

    /**
     * Test que la fonction countFilms de FilmService renvoie bien le nombre de films qui correspond aux attentes
     * @throws DaoException
     * @throws ServiceException
     */
    @Test
    public void countFilmsTest() throws DaoException , ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film1 = new FilmPojo(1,"1", 1,1);
        long total = 1;
        listFilm.add(film1);
        when(filmDAO.countFilms()).thenReturn(listFilm.size());
        long result = filmService.countFilms();
        assertEquals((long) 1, result);
    }


     /**
     * Test que la fonction getTotalWithFilter de FilmService renvoie bien le nombre de pages qui correspond aux attentes (rq on pourrait tester avec plus de 10)
     * @throws DaoException
     * @throws ServiceException
     */
    @Test
    public void getTotalWithFilterTest() throws DaoException , ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film1 = new FilmPojo(1,"1",1, 1);
        long total = 1;
        int size = 10;
        String titre = "1";
        String real = "";
        listFilm.add(film1);
        when(filmDAO.countFilmsFiltered(titre,real)).thenReturn(listFilm.size());
        long result = filmService.getTotalWithFilter(titre,real,size);
        assertEquals((long) 1,result);
    }

    /**
     * Test que la fonction findById de FilmService renvoie bien un FilmDTO à partir d'un FilmPojo qui correspond aux attentes
     * @throws DaoException
     * @throws ServiceException
     */
    @Test
    public void findByIdTest() throws DaoException , ServiceException {
        FilmPojo film1 = new FilmPojo(1,"1",1, 1);
        FilmDTO filmResult1 = new FilmDTO(1,"1", 1,1," 1");
        Date date = new Date(0001,01,01);
        RealisateurPojo real1 = new RealisateurPojo(1,"1","",date);
        String id = "1";
        String idreal = "1";
        when(filmDAO.findById(id)).thenReturn(film1);
        when(realisateurDAO.findById(idreal)).thenReturn(real1);
        FilmDTO result = filmService.findById(id);
        assertEquals(filmResult1, result);
    }


    /**
     * Test que la fonction findWithFilter de FilmService renvoie bien une liste de FilmDTO à partir d'une liste de FilmPojo qui correspond aux attentes
     * @throws DaoException
     * @throws ServiceException
     */
    @Test
    public void findWithFilterTest() throws DaoException , ServiceException {
        List<FilmPojo> listFilm = new ArrayList<>();
        FilmPojo film1 = new FilmPojo(1,"1",1, 1);
        List<FilmDTO> listFilmResult = new ArrayList<>();
        FilmDTO filmResult1 = new FilmDTO(1,"1", 1,1," 1");
        List<RealisateurPojo> listReal = new ArrayList<>();
        Date date = new Date(0001,01,01);
        RealisateurPojo real1 = new RealisateurPojo(1,"1","",date);
        int number = 1;
        int size = 10;
        String titre = "1";
        String real = "";
        listFilmResult.add(filmResult1);
        listFilm.add(film1);
        listReal.add(real1);
        when(realisateurDAO.findAll()).thenReturn(listReal);
        when(filmDAO.findWithFilter(titre,real,number,size)).thenReturn(listFilm);
        List<FilmDTO> resultList = filmService.findWithFilter(titre,real,number,size);
        assertEquals(1, resultList.size());
        assertEquals(filmResult1, resultList.get(0));
    }
}