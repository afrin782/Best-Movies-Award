package com.example.demo.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movies;

@Repository
public class MovieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // CREATE
    public int save(Movies movies) {

        String sql = "INSERT INTO movies " +
                     "(movie_name, language, award_category, director, actor, award_year) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                movies.getMovieName(),
                movies.getLanguage(),
                movies.getAwardCategory(),
                movies.getDirector(),
                movies.getActor(),
                movies.getAwardYear());
    }

    // READ
    public List<Movies> getAllMovies() {

        String sql = "SELECT * FROM movies";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Movies movie = new Movies();

            movie.setId(rs.getInt("id"));
            movie.setMovieName(rs.getString("movie_name"));
            movie.setLanguage(rs.getString("language"));
            movie.setAwardCategory(rs.getString("award_category"));
            movie.setDirector(rs.getString("director"));
            movie.setActor(rs.getString("actor"));
            movie.setAwardYear(rs.getInt("award_year"));

            return movie;
        });
    }

    // READ ONE
    public Movies getMovieById(int id) {

        String sql = "SELECT * FROM movies WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

            Movies movie = new Movies();

            movie.setId(rs.getInt("id"));
            movie.setMovieName(rs.getString("movie_name"));
            movie.setLanguage(rs.getString("language"));
            movie.setAwardCategory(rs.getString("award_category"));
            movie.setDirector(rs.getString("director"));
            movie.setActor(rs.getString("actor"));
            movie.setAwardYear(rs.getInt("award_year"));

            return movie;

        }, id);
    }

    // UPDATE
    public int update(Movies movie) {

        String sql = "UPDATE movies SET " +
                     "movie_name=?, language=?, award_category=?, " +
                     "director=?, actor=?, award_year=? " +
                     "WHERE id=?";

        return jdbcTemplate.update(sql,
                movie.getMovieName(),
                movie.getLanguage(),
                movie.getAwardCategory(),
                movie.getDirector(),
                movie.getActor(),
                movie.getAwardYear(),
                movie.getId());
    }

    // DELETE
    public int delete(int id) {

        String sql = "DELETE FROM movies WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
}