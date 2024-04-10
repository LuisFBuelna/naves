package com.buelna.mapper;

import com.buelna.dtos.NaveDTO;
import com.buelna.entities.Nave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NaveMapper {

    NaveMapper mapper = Mappers.getMapper(NaveMapper.class);

    List<NaveDTO> listaToListaDto (List<Nave> list);

    NaveDTO naveToNaveDto (Nave nave);
}
