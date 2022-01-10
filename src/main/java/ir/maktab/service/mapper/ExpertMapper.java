package ir.maktab.service.mapper;

import ir.maktab.model.dto.ExpertDto;
import ir.maktab.model.entity.Expert;

/**
 * @author Mahsa Alikhani m-58
 */
public class ExpertMapper {

    private ExpertDto expertDto;
    private Expert expert;

    public Expert toExpert(ExpertDto expertDto){
        expert = new Expert();
        expert.setName(expertDto.getName());
        expert.setLastName(expertDto.getLastName());
        expert.setEmail(expertDto.getEmail());
        expert.setUserStatus(expertDto.getUserStatus());
        expert.setCredit(expertDto.getCredit());
        expert.setImageData(expertDto.getImageData());
        expert.setMeanRate(expertDto.getMeanRate());
        return expert;
    }

    public ExpertDto toExpertDto(Expert expert){
        expertDto = new ExpertDto();
        expertDto.setName(expert.getName());
        expertDto.setLastName(expert.getLastName());
        expertDto.setEmail(expert.getEmail());
        expertDto.setUserStatus(expert.getUserStatus());
        expertDto.setCredit(expert.getCredit());
        expertDto.setImageData(expert.getImageData());
        expertDto.setMeanRate(expert.getMeanRate());
        expertDto.setRegistrationDate(expert.getRegistrationDate());
        return expertDto;
    }
}
