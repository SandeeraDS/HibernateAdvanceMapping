package org.ds.advance_mapping_1.service;

import jakarta.transaction.Transactional;
import org.ds.advance_mapping_1.Exception.ClientException;
import org.ds.advance_mapping_1.Exception.ServerException;
import org.ds.advance_mapping_1.bean.InstructorBean;
import org.ds.advance_mapping_1.bean.InstructorDetailsBean;
import org.ds.advance_mapping_1.dto.InstructorDTO;
import org.ds.advance_mapping_1.repository.InstructorRepository;
import org.ds.advance_mapping_1.utils.InstructorPopulator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    
    private final InstructorRepository instructorRepository;
    
    public InstructorService(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public InstructorDTO addInstructor(InstructorDTO newInstructor) {

        if (newInstructor == null || newInstructor.getFirstName() == null || newInstructor.getFirstName().isBlank()
                || newInstructor.getLastName() == null || newInstructor.getLastName().isBlank()
                || newInstructor.getEmail() == null || newInstructor.getEmail().isBlank()) {
            throw new ClientException("invalid input data.");
        }

        try {
            InstructorBean instructorBean = InstructorPopulator.populateInstructorBean(newInstructor, false);
            instructorRepository.persist(instructorBean);
            newInstructor.setId(instructorBean.getId());
            return newInstructor;
        } catch (Exception e) {
            throw new ServerException("Error occurred when adding a new instructor.");
        }
    }

    @Transactional
    public InstructorDTO findByInstructorId(long id) {
        if (id > 0) {
            InstructorBean instructorBean;
            try {
                instructorBean = instructorRepository.getById(id);
            } catch (Exception e) {
                throw new ServerException("Error occurred when getting instructor.");
            }
            if (instructorBean != null) {
                return InstructorPopulator.populateInstructorDTO(instructorBean);
            } else {
                throw new ClientException("Instructor not found for given id.");
            }
        } else {
            throw new ClientException("invalid Instructor Id.");
        }
    }

    @Transactional
    public List<InstructorDTO> findByAllInstructor() {
        try {
            List<InstructorBean> instructorBeanList = instructorRepository.getAll();
            return instructorBeanList.stream().map(InstructorPopulator::populateInstructorDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServerException("Error occurred when getting all instructors.");
        }
    }

    @Transactional
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
        if (instructorDTO == null || instructorDTO.getId() < 1 || instructorDTO.getFirstName() == null || instructorDTO.getFirstName().isBlank()
                || instructorDTO.getLastName() == null || instructorDTO.getLastName().isBlank()
                || instructorDTO.getEmail() == null || instructorDTO.getEmail().isBlank()) {
            throw new ClientException("invalid input data.");
        }
        InstructorBean instructorBean;
        try {
            instructorBean = instructorRepository.getById(instructorDTO.getId());
        } catch (Exception e) {
            throw new ServerException("Error occurred when getting instructor.");
        }
        if (instructorBean != null) {
            instructorBean.setFirstName(instructorDTO.getFirstName());
            instructorBean.setLastName(instructorDTO.getLastName());
            instructorBean.setEmail(instructorDTO.getEmail());
            if(instructorBean.getInstructorDetailsBean() !=null){
                instructorBean.getInstructorDetailsBean().setYoutubeChannel(instructorDTO.getYoutubeChannel());
                instructorBean.getInstructorDetailsBean().setHobby(instructorDTO.getHobby());
            } else {
                if((instructorDTO.getYoutubeChannel()!= null && !instructorDTO.getYoutubeChannel().isBlank()) ||
                        (instructorDTO.getHobby()!= null && !instructorDTO.getHobby().isBlank())){
                    InstructorDetailsBean instructorDetailsBean = new InstructorDetailsBean();
                    instructorDetailsBean.setYoutubeChannel(instructorDTO.getYoutubeChannel());
                    instructorDetailsBean.setHobby(instructorDTO.getHobby());
                    instructorBean.setInstructorDetailsBean(instructorDetailsBean);
                }
            }
            try {
                instructorRepository.merge(instructorBean);
            } catch (Exception e) {
                throw new ServerException("Error occurred when updating instructor.");
            }
            return instructorDTO;
        } else {
            throw new ClientException("Instructor not found for given id.");
        }
    }

    @Transactional
    public InstructorDTO deleteByInstructorId(long id) {
        if (id > 0) {
            InstructorBean instructorBean;
            try {
                instructorBean = instructorRepository.getById(id);
            } catch (Exception e) {
                throw new ServerException("Error occurred when finding instructor.");
            }
            if (instructorBean != null) {
                InstructorDTO instructorDTO = InstructorPopulator.populateInstructorDTO(instructorBean);
                try {
                    instructorRepository.remove(instructorBean);
                } catch (Exception e) {
                    throw new ServerException("Error occurred when deleting instructor.");
                }
                return instructorDTO;
            } else {
                throw new ClientException("Instructor not found for given id.");
            }
        } else {
            throw new ClientException("invalid Instructor Id.");
        }
    }

    public InstructorDTO findByInstructorDetailsId(long instructorDetailsId) {
        if (instructorDetailsId > 0) {
            InstructorDetailsBean instructorDetailsBean;
            try {
                instructorDetailsBean = instructorRepository.getByInstructorDetailsId(instructorDetailsId);
            } catch (Exception e) {
                throw new ServerException("Error occurred when getting instructor.");
            }
            if (instructorDetailsBean != null) {
                return InstructorPopulator.populateInstructorDTO(instructorDetailsBean);
            } else {
                throw new ClientException("Instructor not found for given id.");
            }
        } else {
            throw new ClientException("invalid Instructor Id.");
        }
    }

    @Transactional
    public InstructorDTO deleteByInstructorDetailsId(long instructorDetailsId) {
        if (instructorDetailsId > 0) {
            InstructorDetailsBean instructorDetailsBean;
            try {
                instructorDetailsBean = instructorRepository.getByInstructorDetailsId(instructorDetailsId);
            } catch (Exception e) {
                throw new ServerException("Error occurred when finding instructor.");
            }
            if (instructorDetailsBean != null) {
                InstructorDTO instructorDTO = InstructorPopulator.populateInstructorDTO(instructorDetailsBean);
                try {
                    instructorRepository.removeInstructorDetailsId(instructorDetailsBean);
                } catch (Exception e) {
                    throw new ServerException("Error occurred when deleting instructor.");
                }
                return instructorDTO;
            } else {
                throw new ClientException("Instructor not found for given id.");
            }
        } else {
            throw new ClientException("invalid Instructor Id.");
        }
    }
}
