public interface IFachada {
    public String save(DomainEntity entity);
	public String update(DomainEntity entity);
	public String delete(DomainEntity entity);
	public List<DomainEntity> findAll(DomainEntity entity);
    public DomainEntity findById(Long id, Class<? extends DomainEntity> entityClass);
}