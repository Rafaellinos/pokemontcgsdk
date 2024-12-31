package br.com.rafaellino.pokemontcgsdk.client;

import br.com.rafaellino.pokemontcgsdk.client.rest.Resources;
import br.com.rafaellino.pokemontcgsdk.exception.checked.PokemonTcgSdkException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Query {

  private String filter;
  private String orderBy;
  private Integer page;
  private Integer pageSize;
  private Resources resources;
  private String id;

  public String getPath() throws PokemonTcgSdkException {
    StringBuilder stringBuilder = new StringBuilder();
    if (resources == null) {
      throw new PokemonTcgSdkException("Resource is necessary to build the query");
    }
    stringBuilder.append("/").append(resources.getPath());

    if (id != null) {
      return stringBuilder.append("/").append(id).toString();
    }

    HashMap<String, Object> params = new HashMap<>();
    params.put("orderBy", orderBy);
    params.put("q", filter);
    params.put("page", page);
    params.put("pageSize", pageSize);

    boolean first = true;
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      if (entry.getValue() == null) {
        continue;
      }
      if (first) {
        first = false;
        stringBuilder.append("?");
      } else {
        stringBuilder.append("&");
      }
      stringBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
              .append("=")
              .append(URLEncoder.encode(String.valueOf(entry.getValue()), StandardCharsets.UTF_8));
    }
    return stringBuilder.toString();
  }

  public String getFilter() {
    return filter;
  }

  public void setFilter(String filter) {
    this.filter = filter;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Resources getResources() {
    return resources;
  }

  public void setResources(Resources resources) {
    this.resources = resources;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public static QueryBuilder builder() {
    return new QueryBuilder(new Query());
  }

  public static class QueryBuilder {

    private final Query query;

    private QueryBuilder(Query query) {
      this.query = query;
    }

    public static QueryBuilder builder() {
      return new QueryBuilder(new Query());
    }

    public QueryBuilder orderBy(String orderBy) {
      this.query.setOrderBy(orderBy);
      return this;
    }

    public QueryBuilder filter(String filter) {
      this.query.setFilter(filter);
      return this;
    }

    public QueryBuilder page(Integer page) {
      this.query.setPage(page);
      return this;
    }

    public QueryBuilder pageSize(Integer pageSize) {
      this.query.setPageSize(pageSize);
      return this;
    }

    public QueryBuilder resource(Resources resources) {
      this.query.setResources(resources);
      return this;
    }

    public QueryBuilder id(String id) {
      this.query.setId(id);
      return this;
    }

    public Query build() {
      return this.query;
    }

  }

}
