package com.example.processor;

import com.example.dbo.Person;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@Component
public class PersonResourceProcessor implements ResourceProcessor<Resource<Person>> {
    @Override
    public final Resource<Person> process(final Resource<Person> resource) {
        final String idStr = Long.toString(resource.getContent().getId());
        final UriComponents uriComponents = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("/people/{id}/photo").
                buildAndExpand(idStr);
        final String uri = uriComponents.toUriString();
        resource.add(new Link(uri, "photo"));
        return resource;
    }
}
