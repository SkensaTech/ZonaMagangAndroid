package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_bidang_industri
{
  private Integer id_industri;
  private Integer id_bidang;
  private String ownerId;
  private Integer id_bidang_industri;
  private java.util.Date updated;
  private String objectId;
  private java.util.Date created;
  public Integer getId_industri()
  {
    return id_industri;
  }

  public void setId_industri( Integer id_industri )
  {
    this.id_industri = id_industri;
  }

  public Integer getId_bidang()
  {
    return id_bidang;
  }

  public void setId_bidang( Integer id_bidang )
  {
    this.id_bidang = id_bidang;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public Integer getId_bidang_industri()
  {
    return id_bidang_industri;
  }

  public void setId_bidang_industri( Integer id_bidang_industri )
  {
    this.id_bidang_industri = id_bidang_industri;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public tb_bidang_industri save()
  {
    return Backendless.Data.of( tb_bidang_industri.class ).save( this );
  }

  public Future<tb_bidang_industri> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_industri> future = new Future<tb_bidang_industri>();
      Backendless.Data.of( tb_bidang_industri.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<tb_bidang_industri> callback )
  {
    Backendless.Data.of( tb_bidang_industri.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( tb_bidang_industri.class ).remove( this );
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
      Backendless.Data.of( tb_bidang_industri.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( tb_bidang_industri.class ).remove( this, callback );
  }

  public static tb_bidang_industri findById( String id )
  {
    return Backendless.Data.of( tb_bidang_industri.class ).findById( id );
  }

  public static Future<tb_bidang_industri> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_industri> future = new Future<tb_bidang_industri>();
      Backendless.Data.of( tb_bidang_industri.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<tb_bidang_industri> callback )
  {
    Backendless.Data.of( tb_bidang_industri.class ).findById( id, callback );
  }

  public static tb_bidang_industri findFirst()
  {
    return Backendless.Data.of( tb_bidang_industri.class ).findFirst();
  }

  public static Future<tb_bidang_industri> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_industri> future = new Future<tb_bidang_industri>();
      Backendless.Data.of( tb_bidang_industri.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<tb_bidang_industri> callback )
  {
    Backendless.Data.of( tb_bidang_industri.class ).findFirst( callback );
  }

  public static tb_bidang_industri findLast()
  {
    return Backendless.Data.of( tb_bidang_industri.class ).findLast();
  }

  public static Future<tb_bidang_industri> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_bidang_industri> future = new Future<tb_bidang_industri>();
      Backendless.Data.of( tb_bidang_industri.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<tb_bidang_industri> callback )
  {
    Backendless.Data.of( tb_bidang_industri.class ).findLast( callback );
  }

  public static BackendlessCollection<tb_bidang_industri> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( tb_bidang_industri.class ).find( query );
  }

  public static Future<BackendlessCollection<tb_bidang_industri>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<tb_bidang_industri>> future = new Future<BackendlessCollection<tb_bidang_industri>>();
      Backendless.Data.of( tb_bidang_industri.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_bidang_industri>> callback )
  {
    Backendless.Data.of( tb_bidang_industri.class ).find( query, callback );
  }
}