package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_industri
{
  private String objectId;
  private Integer kuota;
  private String provinsi;
  private java.util.Date updated;
  private String ownerId;
    private Integer is_verified;
  private Integer id_user;
  private String nama;
  private java.util.Date created;
  private String kualifikasi;
  private String kota;
  private String alamat;
  private String no_telp;
  private Integer id_industri;
  private String jobdesc;
  private String profil;
  private String logo;
  private String email;
  public String getObjectId()
  {
    return objectId;
  }

  public String getLogo() {
    return logo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getKuota()
  {
    return kuota;
  }

  public void setKuota( Integer kuota )
  {
    this.kuota = kuota;
  }

  public String getProvinsi()
  {
    return provinsi;
  }

  public void setProvinsi( String provinsi )
  {
    this.provinsi = provinsi;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public Integer getIs_verified()
  {
    return is_verified;
  }

  public void setIs_verified( Integer is_verified )
  {
    this.is_verified = is_verified;
  }

  public Integer getId_user()
  {
    return id_user;
  }

  public void setId_user( Integer id_user )
  {
    this.id_user = id_user;
  }

  public String getNama()
  {
    return nama;
  }

  public void setNama( String nama )
  {
    this.nama = nama;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getKualifikasi()
  {
    return kualifikasi;
  }

  public void setKualifikasi( String kualifikasi )
  {
    this.kualifikasi = kualifikasi;
  }

  public String getKota()
  {
    return kota;
  }

  public void setKota( String kota )
  {
    this.kota = kota;
  }

  public String getAlamat()
  {
    return alamat;
  }

  public void setAlamat( String alamat )
  {
    this.alamat = alamat;
  }

  public String getNo_telp()
  {
    return no_telp;
  }

  public void setNo_telp( String no_telp )
  {
    this.no_telp = no_telp;
  }

  public Integer getId_industri()
  {
    return id_industri;
  }

  public void setId_industri( Integer id_industri )
  {
    this.id_industri = id_industri;
  }

  public String getJobdesc()
  {
    return jobdesc;
  }

  public void setJobdesc( String jobdesc )
  {
    this.jobdesc = jobdesc;
  }

  public String getProfil()
  {
    return profil;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public void setProfil(String profil )
  {
    this.profil = profil;
  }
  public void setObjectId( String objectId ) {
    this.objectId = objectId;
  }

  public tb_industri(){

}
                                                    
  public tb_industri save()
  {
    return Backendless.Data.of( tb_industri.class ).save( this );
  }

  public Future<tb_industri> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_industri> future = new Future<tb_industri>();
      Backendless.Data.of( tb_industri.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<tb_industri> callback )
  {
    Backendless.Data.of( tb_industri.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( tb_industri.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( tb_industri.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( tb_industri.class ).remove( this, callback );
  }

  public static tb_industri findById( String id )
  {
    return Backendless.Data.of( tb_industri.class ).findById( id );
  }

  public static Future<tb_industri> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_industri> future = new Future<tb_industri>();
      Backendless.Data.of( tb_industri.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<tb_industri> callback )
  {
    Backendless.Data.of( tb_industri.class ).findById( id, callback );
  }

  public static tb_industri findFirst()
  {
    return Backendless.Data.of( tb_industri.class ).findFirst();
  }

  public static Future<tb_industri> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_industri> future = new Future<tb_industri>();
      Backendless.Data.of( tb_industri.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<tb_industri> callback )
  {
    Backendless.Data.of( tb_industri.class ).findFirst( callback );
  }

  public static tb_industri findLast()
  {
    return Backendless.Data.of( tb_industri.class ).findLast();
  }

  public static Future<tb_industri> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_industri> future = new Future<tb_industri>();
      Backendless.Data.of( tb_industri.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<tb_industri> callback )
  {
    Backendless.Data.of( tb_industri.class ).findLast( callback );
  }

  public static BackendlessCollection<tb_industri> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( tb_industri.class ).find( query );
  }

  public static Future<BackendlessCollection<tb_industri>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<tb_industri>> future = new Future<BackendlessCollection<tb_industri>>();
      Backendless.Data.of( tb_industri.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_industri>> callback )
  {
    Backendless.Data.of( tb_industri.class ).find( query, callback );
  }
}